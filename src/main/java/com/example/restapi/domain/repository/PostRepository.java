package com.example.restapi.domain.repository;

import com.example.restapi.external.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("select p from Post p where p.title = ?1")
//List<Post> findAllByTitle(@Param("title") String title);

//    @Query("select p from Post p where p.title = :title")
//    List<Post> findAllByTitle(String title);

//    @Query("select p from Post p inner join fetch p.comment")//pobiora sie tylko te ktore maja komentarz
//    @Query("select p from Post p left join fetch p.comment")//wszytkie wlacznie z tymi co nie mala komentarza

    /****
     * Zapytanie @Query("select p from Post p left join fetch p.comment") pozbyliśmy się z hibernate n+1 zapytan do bazy ale dostlaiśmy jeszcze w logach cos takiego
     * 2023-07-13 23:11:10.088  WARN 17044 --- [nio-8080-exec-8] o.h.h.internal.ast.QueryTranslatorImpl   : HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
     * powoduje to to że hibernate wykona zpytanie raz ale!!!! np. w pamięci ma 100 tyś. rekordów i z tych 100 tyś. rekordów pobierze ten 5 elementów(tyle sobie ustawiłem teraz w apce)
     * -> nie jest to optymalne
     * */
    @Query("select p from Post p")
    List<Post> findAllPosts(Pageable page);

    /****
     * A teraz troche inaczej
     *
     * */
}
