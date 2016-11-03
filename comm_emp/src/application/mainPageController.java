package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by bbulia on 02.11.16.
 */



public class mainPageController {
//    Image chatlogo = new Image(getClass().getResourceAsStream("/image/chat.png"));
    Image massageL=new Image(getClass().getResourceAsStream("/image/massageLogo.png"));
    Image orderLanchL=new Image(getClass().getResourceAsStream("/image/1.png"));


   @FXML
    private ImageView chatLogo;
    @FXML
    private ImageView massageLogo;
    @FXML
    private ImageView orderLunchLogo;

    @FXML
    private void initialize(){

//        chatLogo.setImage(chatlogo);
        massageLogo.setImage(massageL);
        orderLunchLogo.setImage(orderLanchL);



    }

}
