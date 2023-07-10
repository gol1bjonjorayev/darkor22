package uz.darkor.darkor_22.service.auth.register;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.darkor.darkor_22.criteria.auth.register.RegisterCriteria;
import uz.darkor.darkor_22.dto.auth.register.RegisterCreateDTO;
import uz.darkor.darkor_22.dto.auth.register.RegisterGetDTO;
import uz.darkor.darkor_22.entity.auth.Register;
import uz.darkor.darkor_22.mapper.auth.register.RegisterMapper;
import uz.darkor.darkor_22.repository.auth.register.RegisterRepository;
import uz.darkor.darkor_22.service.AbstractService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class RegisterService extends AbstractService<RegisterMapper, RegisterRepository> {
    private final String directory = "src\\main\\resources\\";
    private final String fileName = "registered_user.xlsx";

    public RegisterService(RegisterMapper mapper, RegisterRepository repository) {
        super(mapper, repository);
    }

    public RegisterGetDTO create(RegisterCreateDTO DTO) {
        Register register = mapper.fromCreateDTO(DTO);
        Register save = repository.save(register);
        return mapper.toGetDTO(save);
    }

    public List<RegisterGetDTO> list(RegisterCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Register> list = repository.findAllOrderByCreatedAtDesc();
        Page<Register> registers = new PageImpl<>(list, request, repository.count());
        return mapper.toListDTO(registers.getContent());
    }

    public Boolean generate() throws Exception {
        int i = 0;
        List<Register> list = repository.findAllOrderByCreatedAtDesc();
        File file = new File(directory + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            sheet.setColumnWidth(0, 2000);
            sheet.setColumnWidth(1, 6000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 6000);
            sheet.setColumnWidth(4, 6000);
            sheet.setColumnWidth(5, 6000);
            sheet.setColumnWidth(6, 6000);
            sheet.setColumnWidth(7, 6000);
            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0, CellType.STRING);
            cell0.setCellValue("N");

            Cell cell1 = row0.createCell(1, CellType.STRING);
            cell1.setCellValue("FULL_NAME");
            Cell cell2 = row0.createCell(2, CellType.STRING);
            cell2.setCellValue("BIRTHDAY");
            Cell cell3 = row0.createCell(3, CellType.STRING);
            cell3.setCellValue("COURSE_NAME");
            Cell cell4 = row0.createCell(4, CellType.STRING);
            cell4.setCellValue("ADDRESS");
            Cell cell5 = row0.createCell(5, CellType.STRING);
            cell5.setCellValue("DATA_TYPE");
            Cell cell6 = row0.createCell(6, CellType.STRING);
            cell6.setCellValue("PHONE_NUMBER");
            Cell cell66 = row0.createCell(7, CellType.STRING);
            cell66.setCellValue("CREATED_AT");

            for (Register register : list) {
                i++;
                Row row = sheet.createRow(i);
                Cell cell100 = row.createCell(0, CellType.STRING);
                cell100.setCellValue(i);
                Cell cell7 = row.createCell(1, CellType.STRING);
                cell7.setCellValue(register.getFullName());
                Cell cell8 = row.createCell(2, CellType.STRING);
                cell8.setCellValue(register.getBirthday());
                Cell cell9 = row.createCell(3, CellType.STRING);
                cell9.setCellValue(register.getCourseName());
                Cell cell10 = row.createCell(4, CellType.STRING);
                cell10.setCellValue(register.getAddress());
                Cell cell11 = row.createCell(5, CellType.STRING);
                cell11.setCellValue(register.getDataType().name());
                Cell cell111 = row.createCell(6, CellType.STRING);
                cell111.setCellValue(register.getPhoneNumber());
                Cell cell112 = row.createCell(7, CellType.STRING);
                cell112.setCellValue(register.getCreatedAt().toLocalDate() + "-" + register.getCreatedAt().toLocalTime());
            }

            OutputStream stream = new FileOutputStream(directory + fileName);
            workbook.write(stream);
            stream.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Resource download() throws Exception {
        Path location = Paths.get(directory + fileName);
        return new UrlResource(location.toUri());
    }
}
