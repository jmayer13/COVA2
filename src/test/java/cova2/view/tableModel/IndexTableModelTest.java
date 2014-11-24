/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cova2.view.tableModel;

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexTableModelTest {

    private IndexTableModel indexTableModel;

    @Before
    public void initialize() {
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        Anime genAnime = new Anime();
        genAnime.setCodeAnime(240);
        genAnime.setCurrentEpisode(2);
        List<Anime> animes = new ArrayList();
        animes.add(onePieceAnime);
        animes.add(genAnime);

        Index onePieceIndex = new Index(1, "One Piece", 21);
        Index genshikenIndex = new Index(2, "Genshiken", 240);
        List<Index> indexes = new ArrayList();
        indexes.add(onePieceIndex);
        indexes.add(genshikenIndex);
        indexTableModel = new IndexTableModel(indexes, animes);

    }

    @Test
    public void testGetIndex() {
        assertTrue("The index is not the same.", indexTableModel.getIndex(0).getCodeIndex() == 1);
    }

    @Test
    public void testGetAnime() {
        assertTrue("The anime is not the same.", indexTableModel.getAnime(0).getCodeAnime() == 21);
    }

}
