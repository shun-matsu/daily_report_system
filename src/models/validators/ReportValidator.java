package models.validators;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportValidator {
    public static List<String> validate(Report r){
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(r.getTitle());
        if(!title_error.equals("")){
            errors.add(title_error);
        }

        String content_error = _validateContent(r.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        String WorkingTime_error = _validateWorkingTime(r.getArrived_at(), r.getLeaved_at());
        if(!WorkingTime_error.equals("")){
            errors.add(WorkingTime_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")){
            return "タイトルを入力してください。";
        }

        return "";

    }

    private static String _validateContent(String content){
        if(content == null || content.equals("")){
            return "内容を入力してください。";
        }

        return "";

    }

    private static String _validateWorkingTime(Time arrived_at, Time leaved_at){
        if((arrived_at == null || arrived_at.equals("")) && leaved_at != null && !leaved_at.equals("")){
            return "出勤時間を入力してください。";
        }
        try{
            if((arrived_at.compareTo(leaved_at)) >= 0){
                return "退勤時間は出勤時間より遅い時間を入力してください。";
            }
        }catch(NullPointerException e){}

        return "";

    }
}
