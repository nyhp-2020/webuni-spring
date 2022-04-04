package hu.webuni.hr.nyhp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.nyhp.dto.CompanyDto;
import hu.webuni.hr.nyhp.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	List<CompanyDto> companiesToDtos(List<Company> companies);

	CompanyDto companyToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);
}
