package ExerciciosTest;

import Exercicios.MapaDispersao.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MapaDispersaoTest {

    @Test
    void testInserirAndBuscar() {
        MapaDispersao<Aluno> mapaDispersao = new MapaDispersao<>(53);
        Aluno obj1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));

        mapaDispersao.inserir(12000, obj1);

        assertEquals(obj1, mapaDispersao.buscar(12000));
    }

    @Test
    void testInserirVariosObjetos() {
        MapaDispersao<Aluno> mapaDispersao = new MapaDispersao<>(53);
        Aluno aluno1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno aluno3 = new Aluno(12500, "Marta", LocalDate.of(2001, 2, 18));
        Aluno aluno4 = new Aluno(13000, "Lucas", LocalDate.of(1998, 11, 25));

        mapaDispersao.inserir(aluno1.getMatricula(), aluno1);
        mapaDispersao.inserir(aluno2.getMatricula(), aluno2);
        mapaDispersao.inserir(aluno3.getMatricula(), aluno3);
        mapaDispersao.inserir(aluno4.getMatricula(), aluno4);

        Aluno[] listaAluno = new Aluno[] { aluno1, aluno2, aluno3, aluno4 };

        for (Aluno aluno : listaAluno) {
            assertEquals(aluno, mapaDispersao.buscar(aluno.getMatricula()));
        }
    }

    @Test
    void testColisao() {
        MapaDispersao<Aluno> mapaDispersao = new MapaDispersao<>(53);
        Aluno aluno1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
        Aluno aluno2 = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno aluno3 = new Aluno(14226, "Marta", LocalDate.of(2001, 2, 18));
        Aluno aluno4 = new Aluno(17180, "Lucas", LocalDate.of(1998, 11, 25));

        mapaDispersao.inserir(aluno1.getMatricula(), aluno1);
        mapaDispersao.inserir(aluno2.getMatricula(), aluno2);
        mapaDispersao.inserir(aluno3.getMatricula(), aluno3);
        mapaDispersao.inserir(aluno4.getMatricula(), aluno4);

        assertEquals(aluno1, mapaDispersao.buscar(12000));
        assertEquals(aluno2, mapaDispersao.buscar(14000));
        assertEquals(aluno3, mapaDispersao.buscar(14226));
        assertEquals(aluno4, mapaDispersao.buscar(17180));
    }
}