
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.io.Buf
import com.twitter.util.Await
import io.finch._

object Application extends App {
  implicit val encodeImage = EncodeResponse[Array[Byte]]("image/png") { u =>
    Buf.ByteArray.Shared(u)
  }
  val plain: Endpoint[Array[Byte]] = get("plain" ? param("uml")) {uml: String =>
    println(uml)
    Ok(Uml.render(uml))
  }

  val file: Endpoint[String] = get("file") {
    Ok("Method Not Implemented")
  }

  val api: Service[Request, Response] = (
    plain :+: file
    ).handle({
    case e => NotFound(new Exception(e))
  }).toService

  Await.ready(Http.serve(":8050", api))
}
