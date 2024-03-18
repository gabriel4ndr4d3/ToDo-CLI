# ToDo CLI

Gerencie suas tarefas direto do terminal.

## Uso

Utilize `todo` seguido do comando desejado e suas opções.

```shell
$ todo <command> [options]
```

Utilize a opção `--help` para obter ajuda.

``` shell
$ todo --help
```
## Comandos
### add

O comando `add` adiciona uma tarefa.

```shell
$ todo add
```

Após sua chamada é solicitado o **título** e a **descrição** da tarefa.

### list

O comando `list` lista todas as tarefas.

```shell
$ todo list
```
### show

Utilize o comando `show` para mostra uma tarefa.

``` shell
$ todo show [task-name]
```

### done

Utilize `done` para marcar uma tarefa como concluída.

``` shell
$ todo done [index]
```

### delete

Utilize `delete` para deletar uma tarefa.

``` shell
$ todo delete [task-name]
```