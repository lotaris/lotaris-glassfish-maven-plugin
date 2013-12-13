package com.lotaris.maven.plugin.glassfish.command;

import com.lotaris.maven.plugin.glassfish.model.Configuration;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

/**
 * The command executor has in charge to execute the command
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class CommandExecutor {
	private static final int PROCESS_LOOP_SLEEP_MILLIS = 100;
	private static final int EXIT_SUCCESS = 0;
	
	/**
	 * Input/Output of the command execution from the {@link ProcessBuilder}
	 */
	private InputStream processOut;
	private InputStream processErr;
	
	/**
	 * The configuration
	 */
	protected Configuration configuration;
	
	/**
	 * ASADMIN dirs
	 */
	private File binDir;
	private File asadmin;
		
	/**
	 * Constructor
	 *
	 * @param configuration The configuration
	 */
	public CommandExecutor(Configuration configuration) {
		this.configuration = configuration;

		binDir = new File(configuration.getGlassfish().getGlassfishDirectory(), "bin");
		asadmin = new File(binDir, "asadmin");
	}

	/**
	 * Execute the command retrieved from the command builder
	 *
	 * @param commandBuilder The command builder
	 * @throws MojoExecutionException
	 * @throws MojoFailureException
	 */
	public void execute(CommandBuilder commandBuilder) throws MojoExecutionException, MojoFailureException {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (System.getProperty("os.name").contains("indows")) {
			File asadminBat = new File(binDir, "asadmin.bat");
			if (asadminBat.exists()) {
				asadmin = asadminBat;
			}
		}

		// Get the logger
		Log log = configuration.getLog();

		// Set the command line to the builder
		processBuilder.command(commandBuilder.getCommandLine(asadmin.getAbsolutePath()));

		try {
			// Do the command
			int exitValue;
			Process process = processBuilder.start();
			processOut = process.getInputStream();
			processErr = process.getErrorStream();
			BufferedReader outReader = new BufferedReader(new InputStreamReader(processOut));

			do {
				try {
					exitValue = process.exitValue();
					break;
				}
				catch (IllegalThreadStateException e) {
					Thread.sleep(PROCESS_LOOP_SLEEP_MILLIS);
				}
				finally {
					while (outReader.ready()) {
						log.info(outReader.readLine());
					}
				}
			} while (true);

			// Check the exit status
			if (exitValue != EXIT_SUCCESS) {
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(processErr));

				while (errorReader.ready()) {
					log.error(errorReader.readLine());
				}

				String errorMessage = commandBuilder.getFriendlyErrorMessage();
				log.error(errorMessage);
				log.error("For more detail on what might be causing the problem try running maven with the --debug option ");
				log.error("or setting the maven-glassfish-plugin [echo] property to [true].");
				throw new MojoFailureException(errorMessage);
			}
		}
		catch (IOException e) {
			throw new MojoExecutionException(commandBuilder.getFriendlyErrorMessage() + " IOException: " + e.getMessage());
		}
		catch (InterruptedException e) {
			throw new MojoExecutionException(commandBuilder.getFriendlyErrorMessage() + " Process was interrupted: " + e.getMessage());
		}
	}		
}
