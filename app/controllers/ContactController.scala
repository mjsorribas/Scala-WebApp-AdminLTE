package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import ejisan.play.libs.{ PageMetaSupport, PageMetaApi }
import courier._, Defaults._

class ContactController @Inject()(
                                   val messagesApi: MessagesApi,
                                   val pageMetaApi: PageMetaApi,
                                   implicit val wja: WebJarAssets
                                 ) extends Controller with I18nSupport with PageMetaSupport {


  /***
    *
    * @return Retorna vista de Formulario de Contacto
    */
  def contactform = Action { implicit request=>
    Ok(views.html.forms.contact())
  }


  def sender  = {

    val mailer = Mailer("smtp.gmail.com", 587)
      .auth(true)
      .as("myapp@gmail.com", "demo1234")
      .startTtls(true)()

    mailer(Envelope.from("you" `@` "gmail.com")
      .to("mom" `@` "gmail.com")
      .cc("dad" `@` "gmail.com")
      .subject("miss you")
      .content(Text("hi mom"))).onSuccess {
      case _ => println("message delivered")
    }
  }
}
