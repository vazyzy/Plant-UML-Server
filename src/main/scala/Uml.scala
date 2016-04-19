import java.io.{ByteArrayOutputStream, StringReader, FileOutputStream}
import java.util
import net.sourceforge.plantuml.preproc.Defines
import net.sourceforge.plantuml.{CharSequence2, BlockUml, BlockUmlBuilder, SourceStringReader}


object Uml {

  def render(data: String): Array[Byte] = {
    val png = new ByteArrayOutputStream
    val reader = new SourceStringReader(data)
    reader.generateImage(png)
    png.toByteArray
  }
}
