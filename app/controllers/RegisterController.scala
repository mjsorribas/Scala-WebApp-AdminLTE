package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import ejisan.play.libs.{ PageMetaSupport, PageMetaApi }
import courier._, Defaults._


class RegisterController @Inject()(
                                    val messagesApi: MessagesApi,
                                    val pageMetaApi: PageMetaApi,
                                    implicit val wja: WebJarAssets
                                  ) extends Controller with I18nSupport with PageMetaSupport {

  def register = Action { implicit request=>
    Ok(views.html.forms.register())
  }

}


