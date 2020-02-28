package com.montaury.citadels;


import com.montaury.citadels.district.Card;
import com.montaury.citadels.player.HumanController;
import com.montaury.citadels.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CityTest {
    private Player player ;
    private Board board ;
    private City city ;
    private CardPile pioche ;

    @BeforeEach
    public void setUp(){
        board = new Board();
        city = new City(board);
        player = new Player("Jean", 20, city, new HumanController());
        player.add(70);
        pioche = new CardPile(Card.all().toList().shuffle());
        player.add(pioche.draw(2));
    }
    @Test
    public void district_construction_cost_test(){
        player.buildDistrict(Card.HARBOR_3);//+4
        player.buildDistrict(Card.CATHEDRAL_1);//+5
        player.buildDistrict(Card.BATTLEFIELD_3);//+3
        player.buildDistrict(Card.WATCHTOWER_1);//+1
        int score=player.score();//le score devrait valoir 13
        assertThat(score).isEqualTo(13);
    }

    @Test
    public void different_disctrict_type_construction_test(){
        player.buildDistrict(Card.HARBOR_3);//+4
        player.buildDistrict(Card.CATHEDRAL_1);//+5
        player.buildDistrict(Card.BATTLEFIELD_3);//+3
        player.buildDistrict(Card.CASTLE_1);//+4
        player.buildDistrict(Card.TREASURY);//+5
        int score=player.score();//le score devrait valoir 21 + 3pts bonus + le nombre de gold du joueur
        assertThat(score).isEqualTo(24+player.gold());
    }

    @Test
    public void construction_cost_complete_city_bonus_test(){
        player.buildDistrict(Card.MANOR_1);//+3
        player.buildDistrict(Card.CASTLE_1);//+4
        player.buildDistrict(Card.PALACE_1);//+5
        player.buildDistrict(Card.WATCHTOWER_1);//+1
        player.buildDistrict(Card.PRISON_1);//+2
        player.buildDistrict(Card.BATTLEFIELD_1);//+3
        player.buildDistrict(Card.FORTRESS_1);//+5
        int score=player.score();//le score devrait valoir 23 + 4 pts bonus
        assertThat(score).isEqualTo(27);
    }

    @Test
    public void construction_cost_complete_city_second_player_test(){
        City firstCity = new City(board);
        firstCity.buildDistrict(Card.TRADING_POST_1);
        firstCity.buildDistrict(Card.PRISON_1);
        firstCity.buildDistrict(Card.TEMPLE_2);
        firstCity.buildDistrict(Card.MAGIC_SCHOOL);
        firstCity.buildDistrict(Card.BATTLEFIELD_1);
        firstCity.buildDistrict(Card.WATCHTOWER_3);
        firstCity.buildDistrict(Card.WATCHTOWER_1);
        player.buildDistrict(Card.MANOR_1);//+3
        player.buildDistrict(Card.CASTLE_1);//+4
        player.buildDistrict(Card.PALACE_1);//+5
        player.buildDistrict(Card.WATCHTOWER_1);//+1
        player.buildDistrict(Card.PRISON_1);//+2
        player.buildDistrict(Card.BATTLEFIELD_1);//+3
        player.buildDistrict(Card.FORTRESS_1);//+5
        int score=player.score(); // le score doit valoir 23 + 2pts
        assertThat(score).isEqualTo(25);
    }

    @Test
    public void constrution_cost_wonders_bonus_test(){
        player.buildDistrict(Card.HARBOR_3);//+4
        player.buildDistrict(Card.CATHEDRAL_1);//+5
        player.buildDistrict(Card.BATTLEFIELD_3);//+3
        player.buildDistrict(Card.DRAGON_GATE);//construction +6 et bonus score +2
        int score=player.score();//le score devrait valoir 20
        assertThat(score).isEqualTo(20);
    }



}