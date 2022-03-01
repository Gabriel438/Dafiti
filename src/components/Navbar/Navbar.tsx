import style from "../Navbar/Navbar.module.css"

export default function Navbar(){
    return (<>
    <nav>
        <div className={style.nav}>
            <div className="col">
                <a href="/">
                    <img src="../assets/img/logo.png" height={25} alt="" />
                </a>
            </div>
            <div className="col">
                <a href="/products">Produtos</a>
            </div>
        </div>
    </nav>
    </>);
}