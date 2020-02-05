package com.montaury.citadels;


import com.montaury.citadels.district.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CityTest {
    @Test
    public void testCoutConstructionQuartiers(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0
        city.buildDistrict(Card.HARBOR_3);//+4
        city.buildDistrict(Card.CATHEDRAL_1);//+5
        city.buildDistrict(Card.BATTLEFIELD_3);//+3
        city.buildDistrict(Card.WATCHTOWER_1);//+1
        int score=city.score(possession);//le score devrait valoir 13
        assertThat(score).isEqualTo(13);
    }

    @Test
    public void testCoutConstructionAvecBonusDifferentsTypesQuartiers(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0
        city.buildDistrict(Card.HARBOR_3);//+4
        city.buildDistrict(Card.CATHEDRAL_1);//+5
        city.buildDistrict(Card.BATTLEFIELD_3);//+3
        city.buildDistrict(Card.CASTLE_1);//+4
        city.buildDistrict(Card.TREASURY);//+5
        int score=city.score(possession);//le score devrait valoir 21 + 3pts bonus
        assertThat(score).isEqualTo(24);
    }

    @Test
    public void testCoutConstructionAvecBonusCiteComplete(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0
        board.mark(city);
        city.buildDistrict(Card.MANOR_1);//+3
        city.buildDistrict(Card.CASTLE_1);//+4
        city.buildDistrict(Card.PALACE_1);//+5
        city.buildDistrict(Card.WATCHTOWER_1);//+1
        city.buildDistrict(Card.PRISON_1);//+2
        city.buildDistrict(Card.BATTLEFIELD_1);//+3
        city.buildDistrict(Card.FORTRESS_1);//+5
        int score=city.score(possession);//le score devrait valoir 23 + 4 pts bonus

        assertThat(score).isEqualTo(27);

    }

    @Test
    public void testCoutConstructionAvecBonusCiteCompleteDeuxiemeJoueur(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0
        city.buildDistrict(Card.MANOR_1);//+3
        city.buildDistrict(Card.CASTLE_1);//+4
        city.buildDistrict(Card.PALACE_1);//+5
        city.buildDistrict(Card.WATCHTOWER_1);//+1
        city.buildDistrict(Card.PRISON_1);//+2
        city.buildDistrict(Card.BATTLEFIELD_1);//+3
        city.buildDistrict(Card.FORTRESS_1);//+5
        int score=city.score(possession); // le score doit valoir 23 + 2pts
        if(city.isComplete() && !board.isFirst(city)) {
            assertThat(score).isEqualTo(25);
        }
    }

    @Test
    public void testCoutConstructionAvecBonusMerveilles(){
        Board board = new Board();
        City city = new City(board);
        Possession possession=new Possession(0,null);//le score est à 0
        city.buildDistrict(Card.HARBOR_3);//+4
        city.buildDistrict(Card.CATHEDRAL_1);//+5
        city.buildDistrict(Card.BATTLEFIELD_3);//+3
        city.buildDistrict(Card.DRAGON_GATE);//construction +6 et bonus score +2
        int score=city.score(possession);//le score devrait valoir 20
        assertThat(score).isEqualTo(20);
    }



}