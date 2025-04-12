package legend.example.project_api_legend.Repository.Login;

import org.springframework.data.jpa.repository.JpaRepository;

import legend.example.project_api_legend.Model.DB_LOGIN;

public interface LoginRepository extends JpaRepository<DB_LOGIN,Long> {
    
}
