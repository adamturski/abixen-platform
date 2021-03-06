/**
 * Copyright (c) 2010-present Abixen Systems. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

(function () {

    'use strict';

    angular
        .module('platformDatabaseConnectionModule')
        .config(platformDatabaseConnectionModuleConfig);

    platformDatabaseConnectionModuleConfig.$inject = [
        '$stateProvider'
    ];

    function platformDatabaseConnectionModuleConfig($stateProvider) {
        $stateProvider
            .state('application.multiVisualization.modules.databaseConnection', {
                url: '/database-connection',
                templateUrl: '/admin/businessintelligence/abixen/multi-visualization/modules/database-connection/html/index.html'
            })
            .state('application.multiVisualization.modules.databaseConnection.list', {
                url: '/list',
                templateUrl: '/admin/businessintelligence/abixen/multi-visualization/modules/database-connection/html/list.html',
                controller: 'DatabaseConnectionListController',
                controllerAs: 'databaseConnectionList'
            })
            .state('application.multiVisualization.modules.databaseConnection.add', {
                url: '/add',
                templateUrl: '/admin/businessintelligence/abixen/multi-visualization/modules/database-connection/html/edit.html',
                controller: 'DatabaseConnectionDetailsController',
                controllerAs: 'databaseConnectionDetails'
            })
            .state('application.multiVisualization.modules.databaseConnection.edit', {
                url: '/edit/:id',
                templateUrl: '/admin/businessintelligence/abixen/multi-visualization/modules/database-connection/html/edit.html',
                controller: 'DatabaseConnectionDetailsController',
                controllerAs: 'databaseConnectionDetails'
            });
    }
})();