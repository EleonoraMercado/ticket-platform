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
    public List<CategoriaModel> getAllCategorie() {
        return categoriaRepository.findAll();
    }

    // Recupera una categoria 
    public CategoriaModel getCategoriaById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con ID: " + id));
    }

    // Crea o aggiorna una categoria
    public CategoriaModel saveOrUpdateCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    // Elimina una categoria 
    public void deleteCategoriaById(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
