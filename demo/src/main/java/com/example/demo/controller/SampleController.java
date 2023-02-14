package com.example.demo.controller;

import com.example.demo.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @GetMapping("/ex1")
    public void ex1() {
        log.info("ex1...................................");
    }

    // @GetMapping의 value 속성값을 '{}'로 처리하면 하나 이상의 URL을 지정할 수 있습니다.
    @GetMapping({"/ex2", "/exLink"})
    // SampleDTO 타입의 객체를 20개 추가하고 이를 Model에 담아서 전송합니다.
    public void exModel(Model model){
        List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            SampleDTO dto = SampleDTO.builder()
                            .sno(i)
                            .first("First.." + i)
                            .last("last.." + i)
                            .regTime(LocalDateTime.now())
                            .build();
                    return dto;
                }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping({"/exInline"})
    public String exInline(RedirectAttributes redirectAttributes){
        log.info("exInline.........................");

        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("First..100")
                .last("Last..100")
                .regTime(LocalDateTime.now())
                .build();
        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public void ex3() {
        log.info("ex3");
    }

    @GetMapping({"/exLayout1", "/exLayout2"})
    public void exLayout1() { log.info("exLayout.........."); }
}