package br.com.tgid.msperson.repositories;

import br.com.tgid.msperson.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query("SELECT w FROM Wallet w JOIN w.person p WHERE p.id = :personId")
    Optional<Wallet> findWallet(final Long personId);
}
