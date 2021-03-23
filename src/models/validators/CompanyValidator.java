package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Company;
import utils.DBUtil;

public class CompanyValidator {
    public static List<String> validate(Company c, Boolean codeDuplicateCheckFlag){
        List<String> errors = new ArrayList<String>();

        String code_error = validateCode(c.getCode(), codeDuplicateCheckFlag);
        if(!code_error.equals("")){
            errors.add(code_error);
        }

        String name_error = validateName(c.getName());
        if(!name_error.equals("")){
            errors.add(name_error);
        }

        String content_error = validateContent(c.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        return errors;
    }

    //取引先コード
    private static String validateCode(String code, Boolean codeDuplicateCheckFlag){
        //必須入力チェック
        if(code == null || code.equals("")){
            return "取引先コードを入力してください";
        }

        //すでに登録されている社員番号との重複チェック
        if(codeDuplicateCheckFlag){
            EntityManager em = DBUtil.createEntityManager();
            long companies_count = (long)em.createNamedQuery("checkRegisteredCompany", Long.class).setParameter("code",code).getSingleResult();
            em.close();
            if(companies_count > 0){
                return "入力された取引先コードの情報はすでに存在しています。";
            }
        }

        return "";
    }

    //会社名の必須入力チェック
    private static String validateName(String name){
        if(name == null || name.equals("")){
            return "会社名を入力してください";
        }

        return "";
    }

    //業務内容の必須入力チェック
    private static String validateContent(String content){
        if(content == null || content.equals("")){
            return "業務内容を入力してください";
        }

        return "";
    }

}
