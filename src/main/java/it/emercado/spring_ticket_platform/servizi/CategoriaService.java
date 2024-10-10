package it.emercado.spring_ticket_platform.servizi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import it.emercado.spring_ticket_platform.model.CategoriaModel;
import it.emercado.spring_ticket_platform.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Recupera tutte le categorie
    public List<CategoriaModel> trovaTutteLeCategorie() {
        return categoriaRepository.findAll();
    }

    // Recupera una categoria devo ancora creare la barra di ricerca
    /*public CategoriaModel trovaCategoriaPerId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con ID: " + id));
    }*/

    // Crea o aggiorna una categoria
    public CategoriaModel salvaCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    // Elimina una categoria 
    public void eliminaCategoriaPerId(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
