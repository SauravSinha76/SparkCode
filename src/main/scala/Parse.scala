object Parse {

  def main(args: Array[String]): Unit = {

    val argumentParser = new ArgumentParser()
    argumentParser.parseArgs(args)

    System.out.println("Parsed arguments...")
    System.out.println(s"mode: ${argumentParser.getMode}")
    System.out.println(s"queueUrl: ${argumentParser.getQueueUrl}")
    System.out.println(s"lakeLocation: ${argumentParser.getLakeLocation}")
  }
}
