package com.rdas.controller

import com.rdas.model.Message
import com.rdas.repo.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.validation.Valid

/**
 * Created by rdas on 04/07/2016.
 */
@Controller
@RequestMapping("/m")
class MessageController {

    @Autowired
    private final MessageRepository messageRepository;

//    public MessageController(MessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }

    //@GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        Iterable<Message> messages = this.messageRepository.findAll();
        return new ModelAndView("messages/list", "messages", messages);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ModelAndView view(@PathVariable("id") Message message) {
        return new ModelAndView("messages/view", "message", message);
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    //@GetMapping(params = "form")
    public String createForm(@ModelAttribute Message message) {
        return "messages/form";
    }

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid Message message, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("messages/form", "formErrors", result.getAllErrors());
        }
        message = this.messageRepository.save(message);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
        return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
//@GetMapping(
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
        Iterable<Message> messages = this.messageRepository.findAll();
        return new ModelAndView("messages/list", "messages", messages);
    }

    @RequestMapping(method = RequestMethod.GET, value = "modify/{id}")
//@GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Message message) {
        return new ModelAndView("messages/form", "message", message);
    }
}
