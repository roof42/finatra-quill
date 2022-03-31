import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import task.Resources

object TaskMainServer extends MainServer

class MainServer extends HttpServer {
  protected def configureHttp(router: HttpRouter): Unit = {
    router.add[Resources]
  }
}
