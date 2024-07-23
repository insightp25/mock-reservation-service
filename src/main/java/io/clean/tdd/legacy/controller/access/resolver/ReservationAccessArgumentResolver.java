//package io.clean.tdd.legacy.controller.access.resolver;
//
//import io.clean.tdd.legacy.controller.access.session.ReservationAccessSessionStorage;
//import io.clean.tdd.legacy.controller.access.annotation.CurrentReservationAccess;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//@Component
//@RequiredArgsConstructor
//public class ReservationAccessArgumentResolver implements HandlerMethodArgumentResolver {
//
//    private final ReservationAccessSessionStorage reservationAccessSessionStorage;
//
//    @Override
//    public boolean supportsParameter(
//        MethodParameter parameter
//    ) {
//        return parameter.hasParameterAnnotation(CurrentReservationAccess.class);
//    }
//
//    @Override
//    public Object resolveArgument(
//        MethodParameter parameter,
//        ModelAndViewContainer mavContainer,
//        NativeWebRequest webRequest,
//        WebDataBinderFactory binderFactory
//    ) {
//        return reservationAccessSessionStorage.getSessionReservationAccessTokenId();
//    }
//}
