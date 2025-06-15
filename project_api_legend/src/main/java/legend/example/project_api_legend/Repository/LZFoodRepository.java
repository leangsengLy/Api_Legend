package legend.example.project_api_legend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import legend.example.project_api_legend.DataModel.Food.FoodFilterDataModel;
import legend.example.project_api_legend.Dto.Food.FoodDetailDto;
import legend.example.project_api_legend.Model.LZFood;
import java.util.List;

public interface LZFoodRepository extends JpaRepository<LZFood,Long> , JpaSpecificationExecutor<LZFood> {
    List<LZFood> findByCinemaId(Long cinemaId);
    @Query(value = "SELECT \r\n" + //
                "f.id FoodId,\r\n" + //
                "f.image_path FoodImagePath,\r\n" + //
                "f.localhost LocalHost,\r\n" + //
                "f.name FoodName ,\r\n" + //
                "f.english_name EnglishName,\r\n" + //
                "f.price Price,\r\n" + //
                "f.qty  Qty,\r\n" + //
                "c.id CinemaId,\r\n" + //
                "c.name CinemaName,\r\n" + //
                "c.en_name CinemaEnglishName,\r\n" + //
                "c.code Code,\r\n" + //
                "c.address Address,\r\n" + //
                "c.path_image CinemaImagePath\r\n" + //
                "FROM DBFOOD f\r\n" + //
                "INNER JOIN DBCINEMA c\r\n" + //
                "ON f.cinema_id = c.id\r\n" + //
                "\r\n" + //
                "",nativeQuery=true)
    List<FoodDetailDto> ListDetail(FoodFilterDataModel filter);
}
