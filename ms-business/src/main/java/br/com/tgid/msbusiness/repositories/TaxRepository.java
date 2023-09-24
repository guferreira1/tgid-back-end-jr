package br.com.tgid.msbusiness.repositories;

import br.com.tgid.msbusiness.models.Taxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Taxes, Long> {
}
