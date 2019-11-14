# Equipo5
Proyecto del equipo 5

### Git-flow
#### carpetas de los branches
```
feature/{nombre_del_branch} -> cuando realizamos un task o creamos una nueva funcionalidad

bugfix/{nombre_del_branch} -> Cuando trabajamos en arreglar bugs antes del release

hotfix/{nombre_del_branch} -> Cuando trabajamos en arreglar bugs despues del release y necesitamos arreglarlo urgentemente
```

#### Nombre de los branches
```
{carpeta}/{codigo_del_ticket}_{Descripcion del ticket}

Ejemplo: 
feature/ABC-001_implementacion-del-drawer-menu
```

#### Nombre de los commits:
Cuando hacemos un commit primero pondremos el codigo del ticket, la razon es porque asi podremos trackear de donde viene una vez mergeamos el branch con develop ya que una vez mergeado el branch se destruira por defecto.
```
Ejemplo:
ABC-001: incluir colores predeterminados en colors.xml
```


