package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import ejisan.play.libs.{ PageMetaSupport, PageMetaApi }
import play.api.db._
import play.twirl.api.Html
import play.api._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import scalikejdbc._


class CustomersController  @Inject()(
                                      db: Database,
                                      val messagesApi: MessagesApi,
                                      val pageMetaApi: PageMetaApi,
                                      implicit val wja: WebJarAssets
                                    ) extends Controller with I18nSupport with PageMetaSupport {
  /**
    * Show index page of customers
    * @return
    */
  def index = Action {
    implicit request =>
      Ok(views.html.customers.index(Html.apply(getcustomers)))
  }

  /**
    * get list of customers
    * @return
    */
  def getcustomers =  {
    var outString = ""
    val conn = db.getConnection()

    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT id, name,lastname from usuarios")
      while (rs.next()) {
        outString += "<tr><td style='width:15%;'><a href='/customer_show/"+rs.getString("id")+"'>"+rs.getString("id")+"</a></td>"
        outString += "<td style='width:55%;'>"+ rs.getString("name").capitalize + ' ' + rs.getString("lastname").capitalize + "</td>"
        outString += "<td style='width:30%;'>"
        outString += "<a class='margin' href='/customer_show/"+rs.getString("id")+"'><i class='fa fa-eye'></i></a>"
        outString += "<a  class='margin' href='/customer_edit/"+rs.getString("id")+"'><i class='fa fa-pencil'></i></a>"
        outString += "<a  class='margin' href='/customer_delete/"+rs.getString("id")+"'><i class='fa fa-trash'></i></a>"
        outString +="</td></tr>"

      }
    } finally {
      conn.close()
    }
    outString
  }

  /**
    * get data of customer
    * @return
    */
  def getcustomerinfo(id:Int) =  {
    var outString = ""
    val conn = db.getConnection()
    val rq =  RequestHeader
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT id, name,lastname, address, celphone, organization from usuarios where id="+id)
      while (rs.next()) {
        outString += "Nombre: " + rs.getString("name").capitalize + ' ' + rs.getString("lastname").capitalize + "<br>"
        outString += "Dirección: " + rs.getString("address").capitalize + "<br>"
        outString += "Telefono: " + rs.getString("celphone").capitalize + "<br>"
        outString += "Empresa: " + rs.getString("organization").capitalize + "<br>"
      }
    } finally {
      conn.close()
    }
    outString
  }

  /**
    * delete data of customer
    * @return
    */
  def trash(id:Int) =  {
    val msg = "<b>Se ha eliminado correctamente</b>"
    val conn = db.getConnection()
    val rq =  RequestHeader
    try {
      val stmt = conn.createStatement
      val rs = stmt.execute("delete from usuarios where id="+id)
    } finally {
      conn.close()
    }
    msg
  }

  /**
    * Show page of customer information
    */
  def show(id:Int) =  Action {

    implicit request =>
      Ok(views.html.customers.show(Html.apply(getcustomerinfo(id))))
  }
  /**
    * Edit page of customer information
    */
  def edit(id:Int) =  Action {
    implicit request =>
      Ok(views.html.customers.edit(id))
  }
  /**
    * Edit page of customer information
    */
  def delete(id:Int) =  Action {
    implicit request =>
      Ok(views.html.customers.delete(Html.apply(trash(id))))
  }

  /**
    * Create page of customer information
    */
  def create =  Action {

    implicit request =>
      Ok(views.html.customers.create())
  }

  /**
    * create customer
    * @return
    */
  def create_customer(name:String, lastname:String) =  {
    val conn = db.getConnection()
    val rq =  RequestHeader
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeUpdate("INSERT INTO usuarios (name,lastname)VALUES('" + name + "','" + lastname + "')")
    } finally {
      conn.close()
    }
    index
  }
  /**
    * update customer
    * @return
    */
  def update_customer(id:Int,name:String,lastname:String) =  {
    val conn = db.getConnection()
    val rq =  RequestHeader
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeUpdate("UPDATE usuarios SET name = '" + name + "',lastname = '" + lastname + "' WHERE id = "+ id)
    } finally {
      conn.close()
    }
    index
  }


  /**
    * get data of customer and format for use in view
    * @return
    */
  def getcustomerinfolist(id:Int) =  {
    val conn = db.getConnection()
    val rq =  RequestHeader
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT id, name,lastname, address, celphone, organization from usuarios where id="+id)

    } finally {
      conn.close()
    }
  }
}
