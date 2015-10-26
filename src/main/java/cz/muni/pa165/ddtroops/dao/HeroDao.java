/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Hero;
import java.util.List;

/**
 *
 * @author Dufkova
 */
public interface HeroDao {
    public Long createHero(Hero hero);
    public List<Hero> getAllHeroes();
    public Hero getHeroById(Long id);
    public void updateHero(Hero hero);
    public void deleteHero(Hero hero);
}
