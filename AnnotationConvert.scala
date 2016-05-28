

package by.bsuir.igor

class AnnotationConvert {

  def convert(gameInfo: GameInfo): String = {
    var result: String = " The score for the game: "
    var i = 0
    result += gameInfo.getScore.toString()

    for (iterator <- gameInfo.getSteps.toCharArray()) {
      i match {
        case 0 =>
          i += 1; convertDir(iterator)
        case 1 =>
          result += ("\n New tiles with a value of " + iterator); i += 1
        case 2 =>
          result += (" in line: " + convertSymb(iterator)); i += 1
        case 3 =>
          result += (" in row: " + convertSymb(iterator)); i += 1
        case _ => i = 0
      }
    }
    def convertDir(sym: Char) {
      sym match {
        case '1' =>
          result += "\n Move left the tiles"
        case '2' =>
          result += "\n Move right the tiles"
        case '3' =>
          result += "\n Movement of the tiles up"
        case '4' =>
          result += "\n Down movement of the tiles"
        case _ => result
      }
    }

    def convertSymb(sym: Char): Char = {
      sym match {
        case '0' => '1'
        case '1' => '2'
        case '2' => '3'
        case '3' => '4'
        case _ => ' '
      }
    }
    result
  }
}
