package bootexample.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "------Counter test application------";
    }

    @RequestMapping("/session/test1")
    public List<String> addToSession(HttpSession session) throws UnknownHostException {
        Integer counter = (Integer) session.getAttribute("counter");
        if(counter==null){
            counter = 0;
        }
        session.setAttribute("counter", ++counter);

        return Stream.of(
                "counter:"+counter,
                "session:"+session.getId(),
                "host:"+InetAddress.getLocalHost().getHostName()
        ).collect(Collectors.toList());
    }

}
