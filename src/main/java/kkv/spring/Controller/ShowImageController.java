
package kkv.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Controller
public class ShowImageController {

    @RequestMapping(value = "/images/{id}/{imgName}")
    @ResponseBody
    public byte[] getImageByName(@PathVariable(value = "id") String id,
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                                 @PathVariable(value = "imgName") String imgName) throws IOException {
=======
=======
>>>>>>> Stashed changes
                           @PathVariable(value = "imgName") String imgName) throws IOException {
>>>>>>> Stashed changes
        System.out.println("check");
        var path = String.format("src/main/webapp/uploads/%s/%s",id,imgName);
        System.out.println(path);
        File serverFile = new File(path);

        return Files.readAllBytes(serverFile.toPath());
        /*createPizzaImagesDirIfNeeded();

        File serverFile = new File(PIZZA_IMAGES_DIR_ABSOLUTE_PATH + imageName + ".jpg");

        return Files.readAllBytes(serverFile.toPath());*/
    }

    @RequestMapping(value = "/images/{resultImageName}")
    @ResponseBody
    public byte[] getImageByCode(@PathVariable(value = "resultImageName") String resultImageName) throws IOException {
        System.out.println("фотки с нейронки");
        return getImageByName("resultN",resultImageName);

    }



}
