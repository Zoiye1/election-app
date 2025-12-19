
export class CustomLogger {

  public date: string = new Date().toLocaleDateString()

  public error(message: string, error: unknown) {

    console.error("ERROR:" + message, error, this.date )

  }

  public warn(message: string) {

    console.warn("WARNING:" + message, this.date )

  }

  public info(message: string) {

    console.info("INFORMATION:" + message, this.date )

  }

  public debug(message: string){
    console.debug("INFORMATION:" + message, this.date )
  }

}
