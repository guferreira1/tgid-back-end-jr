package br.com.tgid.msbusiness.repositories;

import br.com.tgid.msbusiness.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface BusinessRepository extends JpaRepository<Business , Long> {
    @Query("SELECT DISTINCT b FROM Business b JOIN b.taxes t")
    Collection<Business> searchAll();
}
