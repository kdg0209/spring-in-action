# Chapter 02

## 알게된 내용
WebMvcConfigurer 인터페이스를 구현하여 디폴트 url과 페이지를 설정할 수 있습니다.

### WebMvcConfigurer 인터페이스 구현 전
- HomeController를 생성하여 기본 페이지를 보여줄 수 있도록 설정하였습니다.
```
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {

        return "/main/home";
    }
}
```

### WebMvcConfigurer 인터페이스 구현 후
- WebMvcConfigurer 인터페이스를 구현하여 addViewControllers 메서드를 재정의하여 기본 경로와 해당 경로로 접근 시 보여 줄 페이지를 설정할 수 있습니다.
```
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("main/home");
    }
}
```
