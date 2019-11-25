package br.com.totvs.testeSelecao.config.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author tiago marzari
 *
 */
public class FileConfig {
	
	public static final Path PATH_IN = Paths.get("\\data\\in\\");
	public static final Path PATH_OUT = Paths.get("\\data\\out\\");

	public static final String EXTENSION_DAT = ".dat";
	public static final String EXTENSION_DONE_DAT = ".dat.proc";

}
