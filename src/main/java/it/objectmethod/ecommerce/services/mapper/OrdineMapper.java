package it.objectmethod.ecommerce.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.services.dto.OrdineDTO;

@Mapper(componentModel = "spring")
public interface OrdineMapper extends EntityMapper<OrdineDTO, Ordine> {

	@Override
	@Mapping(source = "idOrdine", target = "id")

	OrdineDTO toDto(Ordine entity);

	@Override
	@Mapping(target = "rigaOrdine", ignore = true)
	Ordine toEntity(OrdineDTO dto);
}

//@Component
//public class OrdineMapper {
//
//	@Autowired
//	private UtenteRepository utenteRepo;
//
//	public OrdineDTO toDto(Ordine ordine) {
//		OrdineDTO dto = new OrdineDTO();
//		dto.setId(ordine.getIdOrdine());
//		dto.setDataOrdine(ordine.getDataOrdine());
//		dto.setIdUtente(ordine.getUtente().getIdUtente());
//		dto.setNomeUtente(ordine.getUtente().getNomeUtente());
//		return dto;
//	}
//
//	public Ordine toEntity(OrdineDTO ordineDto) {
//		Ordine ord = new Ordine();
//		ord.setDataOrdine(ordineDto.getDataOrdine());
//		ord.setIdOrdine(ordineDto.getId());
//		Optional<Utente> optUtente = utenteRepo.findById(ordineDto.getIdUtente());
//		Utente utente = optUtente.get();
//		ord.setUtente(utente);
//		return ord;
//	}
//}
