/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.DHS.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/index")
    public String index(Model model) {
        return "admin/admin_index";
    }

}

/*
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private SubcategoriesService subcategoriesService;
    @Autowired
    private VideosService videosService;
    //@Autowired
    //private final StorageService storageService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F://temp//";

    //public AdminController(StorageService storageService) {
    //    this.storageService = storageService;
    //}
    @GetMapping("/index")
    public String index(Model model) {
        List<Categories> getCategories = categoriesService.findAll();
        model.addAttribute("categories", getCategories);
        List<Subcategories> getSubcategories = subcategoriesService.findAll();
        model.addAttribute("subcategories", getSubcategories);
        return "admin/admin_index";
    }

    @RequestMapping("/content")
    public String allContent(Model model) {
        List<Videos> getVideos = videosService.findAll();
        List<Subcategories> getSubcategories = subcategoriesService.findAll();
        model.addAttribute("videos", getVideos);
        model.addAttribute("subcategories", getSubcategories);
        return "admin/admin_content";
    }

    @RequestMapping("/newCategories")
    public String addCategories(Model model) {
        Categories categories = new Categories();
        model.addAttribute("categories", categories);
        return "admin/admin_newCategories";
    }

    @RequestMapping("/newSubcategories")
    public String addSubcategories(Model model) {

        Subcategories theSubcategories = new Subcategories();
        Categories categories = new Categories();

        theSubcategories.setCategories(categories);
        List<Categories> getCategories = categoriesService.findAll();

        model.addAttribute("subcategories", theSubcategories);
        model.addAttribute("categories", getCategories);
        return "admin/admin_newSubcategories";
    }

    @RequestMapping("/newVideos")
    public String addVideos(Model model) {

        Videos theVideos = new Videos();
        Subcategories subcategories = new Subcategories();

        theVideos.setSubcategories(subcategories);
        List<Subcategories> getSubcategories = subcategoriesService.findAll();

        model.addAttribute("videos", theVideos);
        model.addAttribute("subcategories", getSubcategories);
        return "admin/admin_newVideos";
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategories(@ModelAttribute("categories") Categories theCategories) {
        categoriesService.save(theCategories);
        return "redirect:/admin/allCategories";
    }

    @RequestMapping(value = "/saveSubcategory", method = RequestMethod.POST)
    public String saveSubcategories(@ModelAttribute("subcategories") Subcategories theSubcategories) {
        subcategoriesService.save(theSubcategories);
        return "redirect:/admin/allSubcategories";
    }

    @RequestMapping(value = "/saveVideos", method = RequestMethod.POST)
    public String saveVideos(@ModelAttribute("videos") Videos theVideos) {
        videosService.save(theVideos);
        return "redirect:/admin/allVideos";
    }

    @RequestMapping("/allCategories")
    public String getViewCategories(Model model) {
        List<Categories> getCategories = categoriesService.findAll();
        model.addAttribute("categories", getCategories);
        return "admin/admin_allCategories";
    }

    @RequestMapping("/allSubcategories")
    public String getViewSubcategories(Model model) {
        List<Subcategories> getSubcategories = subcategoriesService.findAll();
        List<Categories> getCategories = categoriesService.findAll();
        model.addAttribute("subcategories", getSubcategories);
        model.addAttribute("categories", getCategories);
        return "admin/admin_allSubcategories";
    }

    @RequestMapping("/allVideos")
    public String getViewVideos(Model model) {
        List<Videos> getVideos = videosService.findAll();
        List<Subcategories> getSubcategories = subcategoriesService.findAll();
        model.addAttribute("videos", getVideos);
        model.addAttribute("subcategories", getSubcategories);
        return "admin/admin_allVideos";
    }

    @RequestMapping("/editCategories/{id_category}")
    public ModelAndView editCategory(@PathVariable(name = "id_category") long id_category) {
        ModelAndView mav = new ModelAndView("admin/admin_editCategories");

        Categories theCategories = categoriesService.get(id_category);

        mav.addObject("categories", theCategories);
        return mav;
    }

    @RequestMapping("/editSubcategories/{id_subcategory}")
    public ModelAndView editSubcategory(@PathVariable(name = "id_subcategory") long id_subcategory) {
        ModelAndView mav = new ModelAndView("admin/admin_editSubcategories");

        List<Categories> getCategories = categoriesService.findAll();

        Subcategories theSubcategories = subcategoriesService.get(id_subcategory);

        mav.addObject("subcategories", theSubcategories);
        mav.addObject("categories", getCategories);
        return mav;
    }

    @RequestMapping("/editVideos/{id_video}")
    public ModelAndView editVideos(@PathVariable(name = "id_video") long id_video) {
        ModelAndView mav = new ModelAndView("admin/admin_editVideos");

        List<Subcategories> getSubcategories = subcategoriesService.findAll();

        Videos theVideos = videosService.get(id_video);

        mav.addObject("videos", theVideos);
        mav.addObject("subcategories", getSubcategories);
        return mav;
    }

    @RequestMapping("/deleteCategories/{id_category}")
    public String delete(@PathVariable(name = "id_category") long id_category) {
        categoriesService.delete(id_category);
        return "admin/admin_allCategories";
    }

    @RequestMapping("/deleteSubcategories/{id_subcategory}")
    public String deleteSubcategories(@PathVariable(name = "id_subcategory") long id_subcategory) {
        subcategoriesService.delete(id_subcategory);
        return "admin/admin_allSubcategories";
    }

    @RequestMapping("/deleteVideos/{id_video}")
    public String deleteVideos(@PathVariable(name = "id_video") long id_video) {
        videosService.delete(id_video);
        return "admin/admin_allVideos";
    }

    @GetMapping("/upload")
    public String uploading() {
        return "admin/admin_uploadForm";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "admin/admin_uploadStatus";
    }
    
}
*/
