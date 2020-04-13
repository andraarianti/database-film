/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmdb;

/**
 *
 * @author User
 */
public class MVC {
    ViewFilm viewFilm = new ViewFilm();
    ModelFilm modelFilm = new ModelFilm();
    ControllerFilm controllerFilm = new ControllerFilm(modelFilm, viewFilm);
}
