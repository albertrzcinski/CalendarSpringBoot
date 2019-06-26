package pai2.calendar.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import pai2.calendar.db.UserRepository;
import pai2.calendar.model.EventViewModel;
import pai2.calendar.model.UserModel;

@RestController
@RequestMapping("/email")
public class EmailController {

    private EmailCfg emailCfg;
    private UserRepository userRepository;

    public EmailController(EmailCfg emailCfg, UserRepository userRepository) {
        this.emailCfg = emailCfg;
        this.userRepository = userRepository;
    }

    @PostMapping("/send")
    public void sendFeedback(@RequestBody EventViewModel eventViewModel){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.emailCfg.getHost());
        javaMailSender.setPort(this.emailCfg.getPort());
        javaMailSender.setUsername(this.emailCfg.getUsername());
        javaMailSender.setPassword(this.emailCfg.getPassword());

        if((eventViewModel.getTitle() != null) && (eventViewModel.getStart() != null)) {
            UserModel userModel = this.userRepository.findByUsername(eventViewModel.getUsername());

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("calendar@cal.com");
            simpleMailMessage.setTo(userModel.getEmail());
            simpleMailMessage.setSubject("New reminder from the Calendar application.");

            String tmpStart;

            if (eventViewModel.getStart().length() > 10) {
                tmpStart = eventViewModel.getStart().substring(0, 10) + " at "
                        + eventViewModel.getStart().substring(11, 19);
            } else {
                tmpStart = eventViewModel.getStart();
            }

            String tmpEnd = "";

            if (eventViewModel.getEnd().length() > 10) {
                tmpEnd = eventViewModel.getEnd().substring(0, 10) + " at "
                        + eventViewModel.getEnd().substring(11, 19);
            } else {
                if (eventViewModel.getEnd() != null)
                    tmpEnd = eventViewModel.getEnd();
            }

            simpleMailMessage.setText("You have an event with a title " + eventViewModel.getTitle() + "." +
                    " It starts " + tmpStart + " and ends " + tmpEnd + ".");

            javaMailSender.send(simpleMailMessage);
        }
    }
}
