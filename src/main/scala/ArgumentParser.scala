import org.apache.commons.cli.{BasicParser, CommandLine, CommandLineParser, Options}

/**
 * Provides functionality to parse any command line arguments necessary.
 */
class ArgumentParser extends Serializable {
  private val CLI_MODE_OPTION: String = "m"
  private val CLI_MODE_OPTION_LONG: String = "mode"
  private val CLI_QUEUE_URL_OPTION: String = "q"
  private val CLI_QUEUE_URL_OPTION_LONG: String = "queueUrl"
  private val CLI_LAKE_LOCATION_OPTION: String = "ll"
  private val CLI_LAKE_LOCATION_OPTION_LONG: String = "lakeLocation"
  private val CLI_FILE_UPLOAD_OPTION = "f"
  private val CLI_FILE_UPLOAD_OPTION_LONG = "file"
  private val CLI_FLOW_ID_OPTION = "fl"
  private val CLI_FLOW_ID_LONG = "flowid"
  private val CLI_EXECUTION_ID_OPTION = "e"
  private val CLI_EXECUTION_ID_LONG = "executionId"

  private val cliOptions: Options = new Options()
  cliOptions.addOption(CLI_MODE_OPTION, CLI_MODE_OPTION_LONG, true,
    "Execution mode of the application.")
  cliOptions.addOption(CLI_QUEUE_URL_OPTION, CLI_QUEUE_URL_OPTION_LONG, true,
    "AWS SQS queue URL representing the Ethos ingestion 'Stream Queue'")
  cliOptions.addOption(CLI_LAKE_LOCATION_OPTION, CLI_LAKE_LOCATION_OPTION_LONG, true,
    "Root path to the S3 data 'Lake Location' that will be used to store output files.")
  cliOptions.addOption(CLI_FILE_UPLOAD_OPTION, CLI_FILE_UPLOAD_OPTION_LONG, true,
    "Absolute path to the file that was triggered the File Loader")
  cliOptions.addOption(CLI_FLOW_ID_OPTION, CLI_FLOW_ID_LONG, true,
    "Flow ID of associated file")
  cliOptions.addOption(CLI_EXECUTION_ID_OPTION, CLI_EXECUTION_ID_LONG, true,
    "Execution ID of associated file")

  // Provides access to the parsed command line variables.
  var cliInputs: CommandLine = _

  /**
   * Parses the command line arguments provided.
   * @param args Raw command line arguments to parse.
   */
  def parseArgs(args: Array[String]): Unit = {
    val cliParser: CommandLineParser = new BasicParser()
    cliInputs = cliParser.parse(cliOptions, args)
  }

  def getMode: String = cliInputs.getOptionValue(CLI_MODE_OPTION)
  def getQueueUrl: String = cliInputs.getOptionValue(CLI_QUEUE_URL_OPTION)
  def getLakeLocation: String = cliInputs.getOptionValue(CLI_LAKE_LOCATION_OPTION)
  def getFilePath: String = cliInputs.getOptionValue(CLI_FILE_UPLOAD_OPTION)
  def getFlowId: String = cliInputs.getOptionValue(CLI_FLOW_ID_OPTION)
  def getExecutionId: String = cliInputs.getOptionValue(CLI_EXECUTION_ID_OPTION)
}