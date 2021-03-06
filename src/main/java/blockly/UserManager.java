package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;



@CronapiMetaData(type = "blockly")
@CronappSecurity
public class UserManager {

public static final int TIMEOUT = 300;

/**
 *
 * @param param_Entidade
 * @return Var
 */
// UserManager
public static Var BeforeInsert(Var param_Entidade) throws Exception {
 return new Callable<Var>() {

   // param
   private Var Entidade = param_Entidade;
   // end

   public Var call() throws Exception {
    Entidade = Var.valueOf(Normalize(Entidade));
    Entidade = Var.valueOf(EncryptPassword(Entidade));
    return Entidade;
   }
 }.call();
}

/**
 *
 * @param param_Entidade
 * @return Var
 */
// UserManager
public static Var BeforeUpdate(Var param_Entidade) throws Exception {
 return new Callable<Var>() {

   // param
   private Var Entidade = param_Entidade;
   // end

   public Var call() throws Exception {
    Entidade = Var.valueOf(Normalize(Entidade));
    Entidade = Var.valueOf(EncryptPassword(Entidade));
    return Entidade;
   }
 }.call();
}

/**
 *
 * @param Entidade
 * @return Var
 */
// Descreva esta função...
public static Var EncryptPassword(Var Entidade) throws Exception {
 return new Callable<Var>() {

   private Var ENCRYPT = Var.VAR_NULL;
   private Var password = Var.VAR_NULL;

   public Var call() throws Exception {
    ENCRYPT = Var.valueOf("$2a$10$");
    password = cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("password"));
    if (cronapi.text.Operations.startsWith(password, ENCRYPT).negate().getObjectAsBoolean()) {
        password = cronapi.util.Operations.encryptPassword(password);
        cronapi.object.Operations.setObjectField(Entidade, Var.valueOf("password"), password);
    }
    return Entidade;
   }
 }.call();
}

/**
 *
 * @param Entidade
 * @return Var
 */
// Descreva esta função...
public static Var Normalize(Var Entidade) throws Exception {
 return new Callable<Var>() {

   private Var userName = Var.VAR_NULL;
   private Var email = Var.VAR_NULL;

   public Var call() throws Exception {
    userName = cronapi.text.Operations.normalize(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("userName")));
    email = cronapi.text.Operations.normalize(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("email")));
    cronapi.object.Operations.setObjectField(Entidade, Var.valueOf("normalizedUserName"), userName);
    cronapi.object.Operations.setObjectField(Entidade, Var.valueOf("normalizedEmail"), email);
    return Entidade;
   }
 }.call();
}

/**
 *
 * @return Var
 */
// Descreva esta função...
public static Var obterIdUsuario() throws Exception {
 return new Callable<Var>() {

   private Var identificador = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;

   public Var call() throws Exception {
    identificador = Var.VAR_NULL;
    try {
         identificador = cronapi.database.Operations.getField(cronapi.database.Operations.query(Var.valueOf("app.entity.User"),Var.valueOf("select u.id from User u where u.userName = :userName"),Var.valueOf("userName",cronapi.util.Operations.getCurrentUserName())), Var.valueOf("this[0]"));
     } catch (Exception item_exception) {
          item = Var.valueOf(item_exception);
         System.out.println(item.getObjectAsString());
     }
    return identificador;
   }
 }.call();
}

/**
 *
 * @return Var
 */
// Descreva esta função...
public static Var obterLoginUsuario() throws Exception {
 return new Callable<Var>() {

   private Var Entidade = Var.VAR_NULL;
   private Var userName = Var.VAR_NULL;
   private Var email = Var.VAR_NULL;
   private Var ENCRYPT = Var.VAR_NULL;
   private Var password = Var.VAR_NULL;
   private Var identificador = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;

   public Var call() throws Exception {
    return cronapi.util.Operations.getCurrentUserName();
   }
 }.call();
}

}

