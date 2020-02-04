package com.montaury.citadels;


import com.montaury.citadels.district.Card;
import org.junit.jupiter.api.Test;

class CityTest {
    @Test
    public void testCoutConstructionQuartiers(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0;
        city.buildDistrict(Card.HARBOR_3);//+4
        city.buildDistrict(Card.CATHEDRAL_1);//+5
        city.buildDistrict(Card.BATTLEFIELD_3);//+3
        city.buildDistrict(Card.WATCHTOWER_1);//+1
        int score=city.score(possession);//le score devrait valoir 13
        assert (score==13);
    }

    @Test
    public void testCoutConstructionAvecBonusDifferentsTypesQuartiers(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0;
        city.buildDistrict(Card.HARBOR_3);//+4
        city.buildDistrict(Card.CATHEDRAL_1);//+5
        city.buildDistrict(Card.BATTLEFIELD_3);//+3
        city.buildDistrict(Card.CASTLE_1);//+4
        city.buildDistrict(Card.HAUNTED_CITY);//+2
        int score=city.score(possession);//le score devrait valoir 18 + 3pts bonus
        assert (score==21);
    }

    @Test
    public void testCoutConstructionAvecBonusCiteComplete(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0;
        city.buildDistrict(Card.MANOR_1);//+3
        city.buildDistrict(Card.CASTLE_1);//+4
        city.buildDistrict(Card.PALACE_1);//+5
        city.buildDistrict(Card.WATCHTOWER_1);//+1
        city.buildDistrict(Card.PRISON_1);//+2
        city.buildDistrict(Card.BATTLEFIELD_1);//+3
        city.buildDistrict(Card.FORTRESS_1);//+5
        int score=city.score(possession);//le score devrait valoir 23 + 4 pts bonus
        assert (score==27);
    }

}