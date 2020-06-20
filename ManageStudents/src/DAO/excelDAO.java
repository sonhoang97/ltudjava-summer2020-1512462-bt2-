package DAO;

import ModelEntity.Class;
import ModelEntity.Schedule;
import ModelEntity.Student;

import ModelEntity.Subject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class excelDAO {
    public excelDAO() {
    }

    public List<Student> getStudentsExcel() throws IOException {
        List<Student> students = new ArrayList<Student>();
        ClassDAO clsDAO = new ClassDAO();
        String path = "F:/java/Project/github/ManageStudents/ManageStudents/src/Data/managestudents.xlsx";
        InputStream inputStream = new FileInputStream(new File(path));
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Student std = new Student();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        std.setName((String) getCellValue(cell));
                        break;
                    case 1:
                        std.setMssv(((Double) cellValue).intValue());
                        break;
                    case 2:
                        std.setSex((String) getCellValue(cell));
                        break;
                    case 3:
                        std.setCmnd(((Double) cellValue).intValue());
                        break;
                    case 4:
                        String classId = (String) getCellValue(cell);
                        std.setClassStd(clsDAO.getClass(classId));
                        break;
                    case 5:
                        std.setPassword((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            students.add(std);
        }
        workbook.close();
        inputStream.close();
        return students;
    }
//
    public List<Schedule> getSchedulesExcel() throws IOException {
        List<Schedule> Schedules = new ArrayList<Schedule>();
        String path = "F:/java/Project/github/ManageStudents/ManageStudents/src/Data/managestudents.xlsx";

        ClassDAO clsDAO = new ClassDAO();
        SubjectDAO sbjDAO = new SubjectDAO();

        InputStream inputStream = new FileInputStream(new File(path));
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(3);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Schedule schedule = new Schedule();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        schedule.setClassSch(clsDAO.getClass((String) getCellValue(cell)));
                        break;
                    case 1:
                        schedule.setSubjectSch(sbjDAO.getSubjectDB((String) getCellValue(cell)));
                        break;
                    case 2:
                        schedule.setRoom((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            Schedules.add(schedule);
        }
        workbook.close();
        inputStream.close();
        return Schedules;
    }

    public List<Class> getClassesExcel() throws IOException {
        String path = "F:/java/Project/github/ManageStudents/ManageStudents/src/Data/managestudents.xlsx";

        List<Class> Classes = new ArrayList<Class>();
        InputStream inputStream = new FileInputStream(new File(path));
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(1);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Class cls = new Class();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        cls.setId((String) getCellValue(cell));
                        break;
                    case 1:
                        cls.setClassName((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            Classes.add(cls);
        }
        workbook.close();
        inputStream.close();
        return Classes;
    }

    public List<Subject> getSubjectsExcel() throws IOException {
        String path = "F:/java/Project/github/ManageStudents/ManageStudents/src/Data/managestudents.xlsx";

        List<Subject> Subjects = new ArrayList<Subject>();
        InputStream inputStream = new FileInputStream(new File(path));
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(2);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Subject sbj = new Subject();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        sbj.setId((String) getCellValue(cell));
                        break;
                    case 1:
                        sbj.setSubjectName((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            Subjects.add(sbj);
        }
        workbook.close();
        inputStream.close();
        return Subjects;
    }

    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
