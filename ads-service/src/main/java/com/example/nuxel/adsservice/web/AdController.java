package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.annotations.PageTitle;
import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.service.AdService;
import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import com.example.nuxel.adsservice.validators.ad.AdAddValidator;
import com.example.nuxel.adsservice.web.viewModels.CategoryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ads")
public class AdController extends BaseController {
    private final AdService adService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final AdAddValidator adAddValidator;


    public AdController(AdService adService, CategoryService categoryService, ModelMapper modelMapper, AdAddValidator adAddValidator) {
        this.adService = adService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.adAddValidator = adAddValidator;
    }

//    @GetMapping("/getAllByUserId/{id}")
//    public ModelAndView getAllAdsByUserId (@PathVariable String id, ModelAndView modelAndView){
//        modelAndView.addObject(adService.findAllByUserId(id));
//        return null;
//    }

    @GetMapping("/add")
    @PageTitle("Add ad")
    public ModelAndView addAd(ModelAndView modelAndView) {
        modelAndView.addObject("ad", new AdAddBindingModel());
        allCategories(modelAndView);
        return super.view("/ad/ads-add", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addAd(@Valid @ModelAttribute(name = "ad") AdAddBindingModel adAddBindingModel,
                              BindingResult result) throws IOException {
        adAddValidator.validate(adAddBindingModel,result);
        if (result.hasErrors()){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("ad", adAddBindingModel);
            allCategories(modelAndView);
            return super.view("/ad/ads-add", modelAndView);
        }

        adService.addAd(adAddBindingModel);

        return null;
    }

    private void allCategories(ModelAndView modelAndView) {
        modelAndView.addObject("categories", categoryService.getAllCategories().stream()
                .map(c -> this.modelMapper.map(c, CategoryViewModel.class)).sorted(Comparator.comparing(CategoryViewModel::getName))
                .collect(Collectors.toList()));
    }
}
