package project.projectspring;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Aviscontroller {

    @RequestMapping(value = "/avis", method = RequestMethod.GET)
    public String showHomePage() {
        return "avis"; 
    }
}
