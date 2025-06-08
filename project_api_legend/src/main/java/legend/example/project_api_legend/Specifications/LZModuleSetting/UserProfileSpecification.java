package legend.example.project_api_legend.Specifications.LZModuleSetting;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.Model.LZUserProfile;

public class UserProfileSpecification {
    public static Specification<LZUserProfile> GetDataByLoginId(Long loginId){
        return (root,query,cb)->{
            if (loginId < 1) {
                return cb.conjunction();
            }
            return cb.equal(root.get("LOGIN_ID"), loginId);
        };
    }
}
