import React from 'react';
import {HashRouter, Route, Switch} from 'react-router-dom';
import Login from "./login";
import Register from "./register";

const BasicRoute = () =>(
    <HashRouter>
        <Switch>
            <Route exact path="/" component={Login}/>
            <Route exact path="/register" component={Register}/>
        </Switch>
    </HashRouter>
);

export default BasicRoute;