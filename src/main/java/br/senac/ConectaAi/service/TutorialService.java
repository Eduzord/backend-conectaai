package br.senac.ConectaAi.service;

import br.senac.ConectaAi.dto.request.TutorialDtoRequest;
import br.senac.ConectaAi.dto.request.TutorialDtoRequestUpdate;
import br.senac.ConectaAi.dto.response.TutorialDtoResponse;
import br.senac.ConectaAi.entity.Tutorial;
import br.senac.ConectaAi.repository.TutorialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> listarTutoriais(){
        return this.tutorialRepository.listarTutoriaisAtivos();
    }

    public Tutorial listarTutorialPorId(Integer idTutorial){
        return this.tutorialRepository.obterTutorialAtivoPorId(idTutorial);
    }

    public TutorialDtoResponse salvar(TutorialDtoRequest tutorialDtoRequest){
        Tutorial tutorial = modelMapper.map(tutorialDtoRequest, Tutorial.class);
        tutorial.setStatus(1);

        Tutorial tutorialSalvo = this.tutorialRepository.save(tutorial);
        return modelMapper.map(tutorialSalvo,TutorialDtoResponse.class);
    }

    public TutorialDtoResponse atualizar(Integer idTutorial, TutorialDtoRequestUpdate tutorialDtoRequestUpdate){
        Tutorial tutorial = this.listarTutorialPorId(idTutorial);
        if(tutorial!=null){
            modelMapper.map(tutorialDtoRequestUpdate,tutorial);
            Tutorial tutorialTemp = this.tutorialRepository.save(tutorial);

            return modelMapper.map(tutorialTemp,TutorialDtoResponse.class);
        }else
            return null;
    }

    public void apagar(Integer idTutorial){
        Tutorial tutorial = this.listarTutorialPorId(idTutorial);
        if(tutorial != null)
            this.tutorialRepository.apagadorLogico(idTutorial);
    }
}
