package uz.darkor.darkor_22.mapper.carousel;

import org.mapstruct.Mapper;
import uz.darkor.darkor_22.dto.home.carousel.CarouselCreateDTO;
import uz.darkor.darkor_22.dto.home.carousel.CarouselGetDTO;
import uz.darkor.darkor_22.dto.home.carousel.CarouselUpdateDTO;
import uz.darkor.darkor_22.entity.home.Carousel;
import uz.darkor.darkor_22.mapper.GenericMapper;
import uz.darkor.darkor_22.mapper.system.file.FileMapper;


@Mapper(componentModel = "spring", uses = {FileMapper.class})
public interface CarouselMapper extends GenericMapper<CarouselCreateDTO, CarouselUpdateDTO, CarouselGetDTO, Carousel> {
}
