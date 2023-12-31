package uz.darkor.darkor_22.controller.course.price;

import uz.darkor.darkor_22.controller.GenericCUDController;
import uz.darkor.darkor_22.controller.GenericGLController;
import uz.darkor.darkor_22.criteria.price.PriceCriteria;
import uz.darkor.darkor_22.dto.course.price.PriceCreateDTO;
import uz.darkor.darkor_22.dto.course.price.PriceLocalizedDTO;
import uz.darkor.darkor_22.dto.course.price.PriceUpdateDTO;

import java.util.UUID;

public interface PriceController extends GenericCUDController<PriceCreateDTO, PriceUpdateDTO, PriceLocalizedDTO, UUID>,
        GenericGLController<PriceLocalizedDTO, PriceCriteria, UUID> {
}
