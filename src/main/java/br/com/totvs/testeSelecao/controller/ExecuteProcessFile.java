package br.com.totvs.testeSelecao.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.totvs.testeSelecao.config.constants.FileConfig;
import br.com.totvs.testeSelecao.domain.CalcReport;
import br.com.totvs.testeSelecao.domain.Line;
import br.com.totvs.testeSelecao.service.LineBuilderService;
import br.com.totvs.testeSelecao.service.ReportBuilderService;
import br.com.totvs.testeSelecao.util.Util;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tiago.marzari
 * @since 24/11/2018
 * 
 *        A Watch Service API foi utilizada pois é projetada para aplicativos
 *        que precisam ser notificados sobre eventos de alteração de arquivos.
 *        Ela é adequada para um servidor de aplicativos que monitora um
 *        diretório, talvez esperando .jsp ou .jar, para implantá-los por
 *        exemplo. Chamada de API de serviço de observação, essa API permite que
 *        você registre um diretório (ou diretórios) com o serviço de
 *        observação. Ao se registrar, você informa ao serviço em quais tipos de
 *        evento você está interessado: criação de arquivo, exclusão de arquivo
 *        ou modificação de arquivo. Quando o serviço detecta um evento de
 *        interesse, no nosso caso a criação do arquivo, ele é encaminhado para
 *        o processo registrado. O processo registrado possui um encadeamento
 *        (ou um conjunto de encadeamentos) dedicado à observação de todos os
 *        eventos para os quais se registrou, porém no nosso caso é somente um.
 *        Quando o evento chega, ele é tratado conforme necessário.
 *
 */
@Component
@Slf4j
public class ExecuteProcessFile {

	@Autowired
	private ReportBuilderService reportBuilder;

	@Autowired
	private LineBuilderService lineBuilder;

	// Number of milliseconds to delay before the first execution
	@Scheduled(fixedDelay = 1000)
	public void process() {
		log.info("Aplicação iniciada!");
		try {
			//Cria os diretórios
			ExecuteProcessFile.buildPath();
		} catch (IOException exe) {
			log.error("Erro ao criar diretório local: {}", exe.getMessage());
			return;
		}
		
		log.info("Escutando diretório: {}, adicione algum arquivo para a aplicação iniciar o processamento.", FileConfig.PATH_IN.toString());

		FileSystem fs = FileSystems.getDefault();
		WatchService ws;

		try {
			// Cria o whatcher escutando eventos entry_create = criação de arquivo
			ws = fs.newWatchService();
			FileConfig.PATH_IN.register(ws, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException e) {
			log.error("Erro ao acessar diretorio local: {} --> {}", FileConfig.PATH_IN.toString(), e.getMessage());
			return;
		}

		// Implementação do loop infinito para aguardar eventos de entrada
		for (;;) {
			log.info("Aguardando arquivo ser adicionado para novo processamento...");
			// espera até o evento ocorrer
			WatchKey key;
			try {
				key = ws.take();
			} catch (InterruptedException exe) {
				log.error("Erro inesperado: {}", exe.getMessage());
				return;
			}

			// Varre o poll de eventos disparados
			for (WatchEvent<?> object : key.pollEvents()) {
				// Arquivo foi adicionado ao diretório
				if (object.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					// Caso o arquivo tenha extensão *.dat entra para o processamento
					if (object.context().toString().endsWith(FileConfig.EXTENSION_DAT)) {
						log.info("Arquivo com extensao .dat adicionado ao diretório de escuta, iniciando processamento...");
						try {
							List<String> lines = Util
									.getLinesFile(FileConfig.PATH_IN.toString() + "\\" + object.context().toString());
							List<Line> linesParsed = lineBuilder.LineBuilder(lines);
							CalcReport report = reportBuilder.dataProcess(linesParsed);
							File file = new File(FileConfig.PATH_OUT.toString() + "\\" + object.context().toString());
							log.info("Gerando relatório em: {}", FileConfig.PATH_OUT.toString());
							buildReport(file, report);
							log.info("Relatório gerado com sucesso.");
						} catch (IOException exe) {
							log.error("Ocorreu erro ao ler arquivo: {}", exe.getMessage());
							return;
						}
					}
				}
			}

			// Redefinindo a key -- Esse passo é crucial para continuar capturando eventos.
			// Se a chave não for mais válida o diretório esta inacessivel, então saia do
			// loop.
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
		log.info("Aplicação finalizada! Diretório não esta mais acessivel.");
	}

	private static void buildPath() throws IOException {
		Util.createDirectory(FileConfig.PATH_IN);
		Util.createDirectory(FileConfig.PATH_OUT);
	}

	private static void buildReport(File file, CalcReport report) {
		Util.filePersist(
				FileConfig.PATH_OUT
						.resolve(file.getName().replace(FileConfig.EXTENSION_DAT, FileConfig.EXTENSION_DONE_DAT)),
				report.toString());
	}

}
