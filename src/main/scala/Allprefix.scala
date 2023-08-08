

object Allprefix {
  def allPrefixes(prefixLength: Int, words: LazyList[String]): LazyList[String] = {
    words.map(_.substring(0,prefixLength)).filter(_.length > prefixLength)
  }

  def main(args: Array[String]): Unit = {
    val words = "flow" #:: "flowers" #:: "flew" #:: "flag" #:: "fm" #:: LazyList.empty
    Allprefix.allPrefixes(3, words).foreach(println)
  }
}

